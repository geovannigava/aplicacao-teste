import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ManterFuncionariosComponent } from './manter-funcionarios/manter-funcionarios.component';
import { ManterVeiculosComponent } from './manter-veiculos/manter-veiculos.component';
import { RelatoriosComponent } from './relatorios/relatorios.component';

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
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class ViewsRoutingModule { }
