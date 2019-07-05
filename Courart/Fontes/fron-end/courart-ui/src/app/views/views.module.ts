import { LayoutModule } from '@angular/cdk/layout';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { MatSidenavModule, MatToolbarModule, MatIconModule, MatButtonModule, MatListModule, MatSnackBarModule, MatPaginatorModule, MatTableModule, MatNativeDateModule, MatDatepickerModule, MatInputModule, MatCardModule, MatSelectModule, MatFormFieldModule, MatDialogModule, MatPaginatorIntl, MatProgressSpinnerModule } from '@angular/material';
import {NgxMaskModule, IConfig} from 'ngx-mask';

import { FuncionarioService } from './../services/funcionario/funcionario.service';
import { ViewsRoutingModule } from './views.routing.module';
import { RelatoriosComponent } from './relatorios/relatorios.component';
import { CoreModule } from './../core/core.module';
import { ManterFuncionariosComponent } from './manter-funcionarios/manter-funcionarios.component';
import { ManterVeiculosComponent } from './manter-veiculos/manter-veiculos.component';
import { DialogBoxComponent } from './dialog-box/dialog-box.component';
import { VeiculoService } from '../services/veiculo/veiculo.service';
import { HomeComponent } from './home/home.component';
import { CustomMatPaginatorIntl } from './CustomMatPaginatorIntl';



@NgModule({
  declarations: [
    ManterVeiculosComponent,
    ManterFuncionariosComponent,
    RelatoriosComponent,
    DialogBoxComponent,
    HomeComponent
  ],
  imports: [
    CoreModule,
    CommonModule,
    MatSidenavModule,
    MatToolbarModule,
    BrowserAnimationsModule,
    LayoutModule,
    BrowserModule,
    MatButtonModule,
    MatListModule,
    MatIconModule,
    MatSnackBarModule,
    ViewsRoutingModule,
    HttpClientModule,
    FormsModule,
    MatFormFieldModule,
    MatSelectModule,
    MatCardModule,
    MatInputModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatTableModule,
    MatPaginatorModule,
    NgxMaskModule.forRoot(),
    MatSnackBarModule,
    MatDialogModule,
    MatProgressSpinnerModule
  ],
  exports: [
  ],
  providers: [
    FuncionarioService,
    VeiculoService,
    {provide: MatPaginatorIntl, useClass: CustomMatPaginatorIntl}
  ],
  entryComponents: [DialogBoxComponent],
})
export class ViewsModule { }
