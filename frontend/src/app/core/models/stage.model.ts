export interface StagePFE {
  id?: number;
  sujet: string;
  dateDebut: string;
  dateFin: string;
  description: string;
  objectifs: string;
  solution: string;
  demarcheProjet?: string;
  outilsUtilises?: string;
  anneeUniversitaire: string;
  statut: StatutStage;
  entrepriseId: number;
  entrepriseNom?: string;
  encadrantId: number;
  encadrantNom?: string;
  etudiantIds?: number[];
  etudiantNoms?: string[];
}

export enum StatutStage {
  PROPOSE = 'PROPOSE',
  EN_COURS = 'EN_COURS',
  TERMINE = 'TERMINE',
  ANNULE = 'ANNULE'
}

export interface Etudiant {
  id?: number;
  email: string;
  cne: string;
  nom: string;
  prenom: string;
  telephone: string;
  filiereId: number;
  filiereNom?: string;
}

export interface Filiere {
  id?: number;
  intitule: string;
  responsableId?: number;
  responsableNom?: string;
}

export interface Entreprise {
  id?: number;
  nom: string;
  adresse: string;
  telephone: string;
  email: string;
  ville: string;
  pays: string;
  responsableEmail: string;
  encadrantEntrepriseEmail: string;
  encadrantEntrepriseTelephone: string;
}

export interface Encadrant {
  id?: number;
  nom: string;
  departement: string;
  etablissement: string;
  email: string;
}

export interface DashboardStats {
  totalStages: number;
  totalEtudiants: number;
  totalEntreprises: number;
  totalEncadrants: number;
  stagesEnCours: number;
  stagesTermines: number;
}
