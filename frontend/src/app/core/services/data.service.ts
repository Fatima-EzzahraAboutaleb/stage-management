import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { StagePFE, Etudiant, Filiere, Entreprise, Encadrant, DashboardStats } from '../models/stage.model';
import { ApiService } from './api.service';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  constructor(private apiService: ApiService, private http: HttpClient) {}

  // ============= DASHBOARD =============
  getDashboardStats(): Observable<any> {
    return this.apiService.get('/dashboard/stats');
  }

  // ============= STAGES =============
  getAllStages(): Observable<any> {
    return this.apiService.get('/dashboard/stages');
  }

  getStageById(id: number): Observable<any> {
    return this.apiService.get(`/dashboard/stages/${id}`);
  }

  createStage(stage: StagePFE): Observable<any> {
    return this.apiService.post('/dashboard/stages', stage);
  }

  updateStage(id: number, stage: StagePFE): Observable<any> {
    return this.apiService.put(`/dashboard/stages/${id}`, stage);
  }

  deleteStage(id: number): Observable<any> {
    return this.apiService.delete(`/dashboard/stages/${id}`);
  }

  searchStages(keyword: string): Observable<any> {
    return this.apiService.get(`/dashboard/stages/search/${keyword}`);
  }

  getStagesByStatut(statut: string): Observable<any> {
    return this.apiService.get(`/dashboard/stages/statut/${statut}`);
  }

  assignEtudiantToStage(stageId: number, etudiantId: number): Observable<any> {
    return this.apiService.post(`/dashboard/stages/${stageId}/etudiants/${etudiantId}`, {});
  }

  removeEtudiantFromStage(stageId: number, etudiantId: number): Observable<any> {
    return this.apiService.delete(`/dashboard/stages/${stageId}/etudiants/${etudiantId}`);
  }

  // ============= ETUDIANTS =============
  getAllEtudiants(): Observable<any> {
    return this.apiService.get('/dashboard/etudiants');
  }

  getEtudiantById(id: number): Observable<any> {
    return this.apiService.get(`/dashboard/etudiants/${id}`);
  }

  createEtudiant(etudiant: Etudiant): Observable<any> {
    return this.apiService.post('/dashboard/etudiants', etudiant);
  }

  updateEtudiant(id: number, etudiant: Etudiant): Observable<any> {
    return this.apiService.put(`/dashboard/etudiants/${id}`, etudiant);
  }

  deleteEtudiant(id: number): Observable<any> {
    return this.apiService.delete(`/dashboard/etudiants/${id}`);
  }

  searchEtudiants(keyword: string): Observable<any> {
    return this.apiService.get(`/dashboard/etudiants/search/${keyword}`);
  }

  getEtudiantsByFiliere(filiereId: number): Observable<any> {
    return this.apiService.get(`/dashboard/etudiants/filiere/${filiereId}`);
  }

  // ============= FILIERES =============
  getAllFilieres(): Observable<any> {
    return this.apiService.get('/dashboard/filieres');
  }

  getFiliereById(id: number): Observable<any> {
    return this.apiService.get(`/dashboard/filieres/${id}`);
  }

  createFiliere(filiere: Filiere): Observable<any> {
    return this.apiService.post('/dashboard/filieres', filiere);
  }

  updateFiliere(id: number, filiere: Filiere): Observable<any> {
    return this.apiService.put(`/dashboard/filieres/${id}`, filiere);
  }

  deleteFiliere(id: number): Observable<any> {
    return this.apiService.delete(`/dashboard/filieres/${id}`);
  }

  // ============= ENTREPRISES =============
  getAllEntreprises(): Observable<any> {
    return this.apiService.get('/dashboard/entreprises');
  }

  getEntrepriseById(id: number): Observable<any> {
    return this.apiService.get(`/dashboard/entreprises/${id}`);
  }

  createEntreprise(entreprise: Entreprise): Observable<any> {
    return this.apiService.post('/dashboard/entreprises', entreprise);
  }

  updateEntreprise(id: number, entreprise: Entreprise): Observable<any> {
    return this.apiService.put(`/dashboard/entreprises/${id}`, entreprise);
  }

  deleteEntreprise(id: number): Observable<any> {
    return this.apiService.delete(`/dashboard/entreprises/${id}`);
  }

  searchEntreprises(keyword: string): Observable<any> {
    return this.apiService.get(`/dashboard/entreprises/search/${keyword}`);
  }

  // ============= ENCADRANTS =============
  getAllEncadrants(): Observable<any> {
    return this.apiService.get('/dashboard/encadrants');
  }

  getEncadrantById(id: number): Observable<any> {
    return this.apiService.get(`/dashboard/encadrants/${id}`);
  }

  createEncadrant(encadrant: Encadrant): Observable<any> {
    return this.apiService.post('/dashboard/encadrants', encadrant);
  }

  updateEncadrant(id: number, encadrant: Encadrant): Observable<any> {
    return this.apiService.put(`/dashboard/encadrants/${id}`, encadrant);
  }

  deleteEncadrant(id: number): Observable<any> {
    return this.apiService.delete(`/dashboard/encadrants/${id}`);
  }

  searchEncadrants(keyword: string): Observable<any> {
    return this.apiService.get(`/dashboard/encadrants/search/${keyword}`);
  }
}
