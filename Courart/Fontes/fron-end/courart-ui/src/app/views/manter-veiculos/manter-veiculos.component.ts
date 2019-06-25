import { SelectStatus } from './../../models/enums/atividade-enum';
import { Component, OnInit } from '@angular/core';

import { Veiculo } from './../../models/veiculo';

@Component({
  selector: 'app-manter-veiculos',
  templateUrl: './manter-veiculos.component.html',
  styleUrls: ['../css/views.component.css']
})
export class ManterVeiculosComponent implements OnInit {

  constructor() { }

  veiculo = new Veiculo();

  tipoStatusSelecionado = 'SIM';
  tipoStatus: SelectStatus[] = [
    { value: 'SIM', viewValue: 'Sim' },
    { value: 'NAO', viewValue: 'Não' }
  ];

  ngOnInit() {
    this.veiculo.quantidadePassageiros = 4;
  }

}
