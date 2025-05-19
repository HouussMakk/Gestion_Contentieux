import { Component, OnInit } from '@angular/core';
import { DossierService } from '../../../services/dossier.service';

@Component({
  selector: 'app-dossier-list',
  templateUrl: './dossier-list.component.html',
  styleUrls: ['./dossier-list.component.css']
})
export class DossierListComponent implements OnInit {
  dossiers: any[] = [];

  constructor(private dossierService: DossierService) {}

  ngOnInit() {
    this.loadDossiers();
  }

  loadDossiers() {
    this.dossierService.getAllDossiers().subscribe(
      (data) => {
        this.dossiers = data;
      },
      (error) => {
        console.error('Error loading dossiers:', error);
      }
    );
  }

  getStatusClass(status: string): string {
    const baseClasses = 'px-2 py-1 text-xs font-medium rounded-full ';
    switch (status.toLowerCase()) {
      case 'en cours':
        return baseClasses + 'bg-yellow-100 text-yellow-800';
      case 'terminé':
        return baseClasses + 'bg-green-100 text-green-800';
      case 'urgent':
        return baseClasses + 'bg-red-100 text-red-800';
      default:
        return baseClasses + 'bg-gray-100 text-gray-800';
    }
  }
}