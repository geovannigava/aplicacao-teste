import { LayoutModule } from '@angular/cdk/layout';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { MatSidenavModule, MatToolbarModule, MatIconModule, MatButtonModule, MatListModule, MatSnackBarModule, MatPaginatorModule, MatTableModule, MatNativeDateModule, MatDatepickerModule, MatInputModule, MatCardModule, MatSelectModule, MatFormFieldModule, MatDialogModule } from '@angular/material';
import {NgxMaskModule, IConfig} from 'ngx-mask';

import { ViewsRoutingModule } from './views.routing.module';
import { RelatoriosComponent } from './relatorios/relatorios.component';
import { CoreModule } from './../core/core.module';
import { ManterFuncionariosComponent } from './manter-funcionarios/manter-funcionarios.component';
import { ManterVeiculosComponent } from './manter-veiculos/manter-veiculos.component';
import { DialogBoxComponent } from './dialog-box/dialog-box.component';

export let options: Partial<IConfig> | (() => Partial<IConfig>);

@NgModule({
  declarations: [
    ManterVeiculosComponent,
    ManterFuncionariosComponent,
    RelatoriosComponent,
    DialogBoxComponent
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
    NgxMaskModule.forRoot(options),
    MatSnackBarModule,
    MatDialogModule
  ],
  exports: [

  ],
  entryComponents: [DialogBoxComponent],
})
export class ViewsModule { }
