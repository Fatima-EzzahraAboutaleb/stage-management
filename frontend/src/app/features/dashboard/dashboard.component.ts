import { Component, OnInit, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DataService } from '../../core/services/data.service';
import { DashboardStats } from '../../core/models/stage.model';
import { LucideAngularModule } from 'lucide-angular';
import { ChartConfiguration } from 'chart.js';
import { BaseChartDirective, NgChartsModule } from 'ng2-charts';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, LucideAngularModule, NgChartsModule],
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  @ViewChild('stagesChart') stagesChart!: BaseChartDirective;
  @ViewChild('statusChart') statusChart!: BaseChartDirective;

  stats: DashboardStats | null = null;
  isLoading = true;

  // Chart data
  stagesChartConfig: ChartConfiguration = {
    type: 'bar',
    data: {
      labels: [],
      datasets: []
    },
    options: {
      responsive: true,
      maintainAspectRatio: true,
      plugins: {
        legend: {
          display: true,
          position: 'top'
        }
      },
      scales: {
        y: {
          beginAtZero: true,
          ticks: {
            stepSize: 1
          }
        }
      }
    }
  };

  statusChartConfig: ChartConfiguration = {
    type: 'doughnut',
    data: {
      labels: ['En cours', 'Terminés', 'Proposés', 'Annulés'],
      datasets: [{
        data: [],
        backgroundColor: ['#3b82f6', '#10b981', '#f59e0b', '#ef4444'],
        borderColor: ['#1e3a8a', '#065f46', '#b45309', '#7f1d1d'],
        borderWidth: 2
      }]
    },
    options: {
      responsive: true,
      maintainAspectRatio: true,
      plugins: {
        legend: {
          position: 'bottom'
        }
      }
    }
  };

  constructor(
    private dataService: DataService,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    this.loadDashboardData();
  }

  loadDashboardData(): void {
    this.dataService.getDashboardStats().subscribe({
      next: (response) => {
        if (response && response.data) {
          this.stats = response.data;
          this.updateCharts();
        }
        this.isLoading = false;
      },
      error: (error) => {
        console.error('Error loading dashboard stats:', error);
        this.toastr.error('Erreur lors du chargement des statistiques');
        this.isLoading = false;
      }
    });
  }

  private updateCharts(): void {
    if (!this.stats) return;

    // Update status chart
    if (this.statusChartConfig.data && this.statusChartConfig.data.datasets) {
      this.statusChartConfig.data.datasets[0].data = [
        this.stats.stagesEnCours,
        this.stats.stagesTermines,
        this.stats.totalStages - this.stats.stagesEnCours - this.stats.stagesTermines,
        0
      ];
    }
  }
}
