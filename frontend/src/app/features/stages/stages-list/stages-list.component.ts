import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { DataService } from '../../../core/services/data.service';
import { StagePFE } from '../../../core/models/stage.model';
import { LucideAngularModule } from 'lucide-angular';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-stages-list',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink, LucideAngularModule],
  templateUrl: './stages-list.component.html',
  styleUrls: ['./stages-list.component.css']
})
export class StagesListComponent implements OnInit {
  stages: StagePFE[] = [];
  filteredStages: StagePFE[] = [];
  isLoading = true;
  searchQuery = '';
  selectedStatut = '';
  currentPage = 1;
  pageSize = 10;
  showFormModal = false;
  editingStage: StagePFE | null = null;

  statutOptions = ['PROPOSE', 'EN_COURS', 'TERMINE', 'ANNULE'];

  constructor(
    private dataService: DataService,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    this.loadStages();
  }

  loadStages(): void {
    this.dataService.getAllStages().subscribe({
      next: (response) => {
        if (response && response.data) {
          this.stages = Array.isArray(response.data) ? response.data : [response.data];
          this.applyFilters();
        }
        this.isLoading = false;
      },
      error: (error) => {
        console.error('Error loading stages:', error);
        this.toastr.error('Erreur lors du chargement des stages');
        this.isLoading = false;
      }
    });
  }

  applyFilters(): void {
    this.filteredStages = this.stages.filter(stage => {
      const matchesSearch = stage.sujet.toLowerCase().includes(this.searchQuery.toLowerCase()) ||
                          stage.description.toLowerCase().includes(this.searchQuery.toLowerCase());
      const matchesStatut = !this.selectedStatut || stage.statut === this.selectedStatut;
      return matchesSearch && matchesStatut;
    });
    this.currentPage = 1;
  }

  onSearch(): void {
    this.applyFilters();
  }

  openFormModal(stage?: StagePFE): void {
    this.editingStage = stage || null;
    this.showFormModal = true;
  }

  closeFormModal(): void {
    this.showFormModal = false;
    this.editingStage = null;
  }

  onFormSubmit(): void {
    this.loadStages();
    this.closeFormModal();
  }

  deleteStage(stage: StagePFE): void {
    if (!stage.id) return;
    
    if (confirm('Êtes-vous sûr de vouloir supprimer ce stage?')) {
      this.dataService.deleteStage(stage.id).subscribe({
        next: () => {
          this.toastr.success('Stage supprimé avec succès');
          this.loadStages();
        },
        error: (error) => {
          console.error('Error deleting stage:', error);
          this.toastr.error('Erreur lors de la suppression');
        }
      });
    }
  }

  getStatutBadgeClass(statut: string): string {
    switch (statut) {
      case 'EN_COURS':
        return 'badge-info';
      case 'TERMINE':
        return 'badge-success';
      case 'PROPOSE':
        return 'badge-warning';
      case 'ANNULE':
        return 'badge-danger';
      default:
        return 'badge-info';
    }
  }

  getStatutLabel(statut: string): string {
    const labels: { [key: string]: string } = {
      'PROPOSE': 'Proposé',
      'EN_COURS': 'En cours',
      'TERMINE': 'Terminé',
      'ANNULE': 'Annulé'
    };
    return labels[statut] || statut;
  }

  get paginatedStages(): StagePFE[] {
    const start = (this.currentPage - 1) * this.pageSize;
    return this.filteredStages.slice(start, start + this.pageSize);
  }

  get totalPages(): number {
    return Math.ceil(this.filteredStages.length / this.pageSize);
  }

  previousPage(): void {
    if (this.currentPage > 1) {
      this.currentPage--;
    }
  }

  nextPage(): void {
    if (this.currentPage < this.totalPages) {
      this.currentPage++;
    }
  }
}
