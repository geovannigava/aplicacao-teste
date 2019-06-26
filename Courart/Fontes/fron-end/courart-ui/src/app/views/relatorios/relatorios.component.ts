import { Component, OnInit } from '@angular/core';

import { FuncionarioService } from './../../services/funcionario/funcionario.service';
import { VeiculoService } from '../../services/veiculo/veiculo.service';

@Component({
  selector: 'app-relatorios',
  templateUrl: './relatorios.component.html',
  styleUrls: ['../css/views.component.css']
})
export class RelatoriosComponent implements OnInit {
  dataInicialFuncionario: Date;
  dataFinalFuncionario: Date;
  dataFinalVeiculo: Date;
  dataInicialVeiculo: Date;

  constructor(private funcionarioService: FuncionarioService,
    private veiculoService: VeiculoService) { }

  ngOnInit() {
  }

  public buscarRelatorioAniversariantes(){
    this.funcionarioService.buscarRelatorioAniversariantes(this.dataInicialFuncionario, this.dataFinalFuncionario)
    .subscribe(response => {
      var file = new Blob([response], { type: 'application/pdf' });
      var fileURL = URL.createObjectURL(file);
      window.open(fileURL);
    });
}

  public buscarRelatorioVeiculosAtivos(){
    console.log(this.dataInicialVeiculo);
    this.veiculoService.buscarRelatorioVeiculosAtivos(this.dataInicialVeiculo, this.dataFinalVeiculo)
    .subscribe(response => {
      var file = new Blob([response], { type: 'application/pdf' });
      var fileURL = URL.createObjectURL(file);
      window.open(fileURL);
    });
}

}
