import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { MatCardModule, MatFormFieldModule, MatSelectModule, MatInputModule, MatDatepickerModule, MatNativeDateModule, MatButtonModule, MatTableModule, MatIcon, MatIconModule, MatPaginatorModule } from '@angular/material';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

import { FuncionarioService } from './services/funcionario/funcionario.service';
import { ManterVeiculosComponent } from './views/manter-veiculos/manter-veiculos.component';
import { ManterFuncionariosComponent } from './views/manter-funcionarios/manter-funcionarios.component';
import { RelatoriosComponent } from './views/relatorios/relatorios.component';

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
    RouterModule.forRoot(routes),
  ],
  declarations: [

  ],
  exports: [RouterModule],
  providers: [
    MatDatepickerModule,
    FuncionarioService
  ]
})
export class AppRoutingModule { }
