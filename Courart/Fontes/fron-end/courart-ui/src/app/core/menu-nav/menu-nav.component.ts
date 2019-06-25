import { Component } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Router } from '@angular/router';

@Component({
  selector: 'app-menu-nav',
  templateUrl: './menu-nav.component.html',
  styleUrls: ['./menu-nav.component.css']
})
export class MenuNavComponent {

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches)
    );

  constructor(private breakpointObserver: BreakpointObserver,
    public router: Router) {}


  clickManterVeiculos(){
    this.router.navigate(['/manter-veiculos']);
  }
  clickManterFuncionarios(){
    this.router.navigate(['/manter-funcionarios']);
  }
  clickRelatorios(){
    this.router.navigate(['/relatorios']);
  }
}
