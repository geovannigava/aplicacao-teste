import { LayoutModule } from '@angular/cdk/layout';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatSidenavModule, MatToolbarModule, MatIconModule, MatButtonModule, MatListModule, MatSnackBarModule } from '@angular/material';

import { MenuNavComponent } from './menu-nav/menu-nav.component';
import { CpfPipe } from './pipes/cpf.pipe';

@NgModule({
  declarations: [
    MenuNavComponent,
    CpfPipe
  ],
  imports: [
    CommonModule,
    MatSidenavModule,
    MatToolbarModule,
    BrowserAnimationsModule,
    LayoutModule,
    BrowserModule,
    MatButtonModule,
    MatListModule,
    MatIconModule,
    MatSnackBarModule
  ],
  exports: [
    MenuNavComponent,
    CpfPipe
  ]
})
export class CoreModule { }
