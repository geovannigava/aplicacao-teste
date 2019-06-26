import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ManterFuncionariosComponent } from './manter-funcionarios/manter-funcionarios.component';
import { ManterVeiculosComponent } from './manter-veiculos/manter-veiculos.component';
import { RelatoriosComponent } from './relatorios/relatorios.component';
import { HomeComponent } from './home/home.component';

const routes: Routes = [
  {
    path: 'manter-veiculos',
    component: ManterVeiculosComponent
  },
  {
    path: 'manter-funcionarios',
    component: ManterFuncionariosComponent
  },
  {
    path: 'relatorios',
    component: RelatoriosComponent
  },
  {
    path: 'home',
    component: HomeComponent
  },
  {
    path: '**',
    redirectTo: 'home'
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class ViewsRoutingModule { }
