import { MatTableDataSource, MatSnackBar, MatDialog, MatPaginator } from '@angular/material';
import { FormControl } from '@angular/forms';
import { Component, OnInit, ChangeDetectorRef, ViewChild, SimpleChanges } from '@angular/core';

import { SelectStatus, AtivoEnum } from './../../models/enums/atividade-enum';
import { Veiculo } from './../../models/veiculo';
import { VeiculoService } from '../../services/veiculo/veiculo.service';
import { DialogBoxComponent } from '../dialog-box/dialog-box.component';
import { Paginacao } from 'src/app/models/paginacao/paginacao';

@Component({
  selector: 'app-manter-veiculos',
  templateUrl: './manter-veiculos.component.html',
  styleUrls: ['../css/views.component.css']
})
export class ManterVeiculosComponent implements OnInit {

  constructor(private changeDetectorRefs: ChangeDetectorRef,
    private veiculoService: VeiculoService,
    public snackBar: MatSnackBar,
    public dialog: MatDialog) { }

  @ViewChild(MatPaginator, {static: false}) paginator: MatPaginator;
  veiculoPaginacao = new Paginacao();
  veiculo = new Veiculo();
  veiculos = [];
  placaBusca: string;
  modeloBusca: string;
  tipoBusca = 'TODOS';
  tipoStatus: SelectStatus[] = [
    { value: 'SIM', viewValue: 'Sim' },
    { value: 'NAO', viewValue: 'Não' }
  ];


  displayedColumns: string[] = ['idVeiculo', 'placa', 'chassi', 'anoFabricacao', 'anoModelo',
  'ativoEnum', 'modelo', 'cor', 'consumoMedio', 'quantidadePassageiros', 'dataCadastro',
  'dataDesativacao', 'editar', 'excluir'];


  ngOnInit() {
    this.veiculoPaginacao.page = 0;
    this.veiculoPaginacao.size = 5;
    this.veiculo.quantidadePassageiros = 4;
    this.buscarTodosVeiculos();
  }

  ngOnChanges(changes: SimpleChanges) {
    this.veiculoPaginacao.size = this.paginator.pageSize != undefined ? this.paginator.pageSize : this.veiculoPaginacao.size;
    if(this.tipoBusca == 'TODOS'){
      this.buscarTodosVeiculos();
    } else if(this.tipoBusca == 'PLACA'){
      this.buscarPorPlaca();
    } else {
      this.buscarPorModelo();
    }
  }

  onPaginator(){
    this.veiculoPaginacao.page = this.paginator.pageIndex;
    this.veiculoPaginacao.size = this.paginator.pageSize != undefined ? this.paginator.pageSize : this.veiculoPaginacao.size;

    if(this.tipoBusca == 'TODOS'){
      this.buscarTodosVeiculos();
    } else if(this.tipoBusca == 'PLACA'){
      this.buscarPorPlaca();
    } else {
      this.buscarPorModelo();
    }
  }

  public onBuscar(tipoBusca: string) {
    this.tipoBusca = tipoBusca;
    this.veiculoPaginacao.page = 0;
    if(this.tipoBusca == 'TODOS'){
      this.buscarTodosVeiculos();
    } else if(this.tipoBusca == 'PLACA'){
      this.buscarPorPlaca();
    } else {
      this.buscarPorModelo();
    }
  }

  public resetarForm(form: FormControl){
    this.veiculo = new Veiculo();
    form.reset();
  }

  private abrirBarraAviso(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 3000,
    });
  }

  public salvarVeiculo(form: FormControl){
    let veiculoSalvo: Veiculo;
    this.veiculoService.salvar(this.veiculo).subscribe( (retorno: Veiculo) => {
      veiculoSalvo = retorno;
      if(veiculoSalvo.idVeiculo != null){
        this.abrirBarraAviso('Veículo Criado.', 'Sucesso');
        this.buscarTodosVeiculos();
      }
    });
    form.reset();
  }

  public selecionarVeiculo(idVeiculo: number) {
    this.veiculoService.buscarPorId(idVeiculo).subscribe( (retorno: Veiculo) => {
      this.veiculo = retorno;
    });
  }

  public atualizarVeiculo(veiculoAtualizado: Veiculo, form: FormControl){
    let veiculoSalvo: Veiculo;
    this.veiculoService.atualizar(veiculoAtualizado).subscribe( (retorno: Veiculo) => {
      veiculoSalvo = retorno;
      if(veiculoSalvo.idVeiculo != null) {
        this.abrirBarraAviso('Dados do Veículo Atualizados.', 'Sucesso');
        this.buscarTodosVeiculos();
      }
    });
    form.reset();
  }

  public dialogoConfirmacaoExclusao(idVeiculo: number): void {
    const dialogRef = this.dialog.open(DialogBoxComponent, {
      width: '250px',
      data: {}
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.excluirVeiculo(idVeiculo);
      }
    });

}

public excluirVeiculo(idVeiculo: number){
  this.veiculoService.remover(idVeiculo).subscribe( (retorno: Boolean) => {
    if(retorno){
      this.abrirBarraAviso('Veículo Excluído.', 'Sucesso');
      this.veiculo = new Veiculo();
      this.buscarTodosVeiculos();
    }
  });
}

public buscarTodosVeiculos() {
  this.veiculoService.listarTodos(this.veiculoPaginacao).subscribe( (retorno) => {
    this.veiculos = retorno.content;
    this.veiculoPaginacao.totalElements = retorno.totalElements;
    this.veiculo = new Veiculo();
  });
  this.changeDetectorRefs.detectChanges();
}


public buscarPorPlaca(){
  if(this.placaBusca != null){
    if(this.placaBusca.length > 1){
      this.veiculoService.listarPorPlaca(this.placaBusca, this.veiculoPaginacao).subscribe( (retorno) => {
        this.veiculos = retorno.content;
        this.veiculoPaginacao.totalElements = retorno.totalElements;
        this.veiculo = new Veiculo();
      });
    }
  }
}

public buscarPorModelo(){
  if(this.modeloBusca != null){
    if(this.modeloBusca.length > 1){
      this.veiculoService.listarPorModelo(this.modeloBusca, this.veiculoPaginacao).subscribe( (retorno) => {
        this.veiculos = retorno.content;
        this.veiculoPaginacao.totalElements = retorno.totalElements;
        this.veiculo = new Veiculo();
      });
    }
  }
}

}
