import { MatTableDataSource, MatSnackBar, MatDialog, MatPaginator } from '@angular/material';
import { FormControl } from '@angular/forms';
import { Component, OnInit, ChangeDetectorRef, ViewChild } from '@angular/core';

import { SelectStatus, AtivoEnum } from './../../models/enums/atividade-enum';
import { Veiculo } from './../../models/veiculo';
import { VeiculoService } from '../../services/veiculo/veiculo.service';
import { DialogBoxComponent } from '../dialog-box/dialog-box.component';

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

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  veiculo = new Veiculo();
  placaBusca: string;
  modeloBusca: string;
  tipoStatus: SelectStatus[] = [
    { value: 'SIM', viewValue: 'Sim' },
    { value: 'NAO', viewValue: 'Não' }
  ];
  selected = 'SIM';

  displayedColumns: string[] = ['idVeiculo', 'placa', 'chassi', 'anoFabricacao', 'anoModelo',
  'ativoEnum', 'modelo', 'cor', 'consumoMedio', 'quantidadePassageiros', 'dataCadastro',
  'dataDesativacao', 'editar', 'excluir'];
  dataSource: MatTableDataSource<Veiculo>;

  ngOnInit() {
    this.buscarTodosVeiculos();
   // this.veiculo.ativoEnum = 0;
    this.veiculo.quantidadePassageiros = 4;
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

  public buscarTodosVeiculos() {
    this.veiculoService.listarTodos().subscribe( (retorno: Veiculo[]) => {
      this.dataSource = new MatTableDataSource<Veiculo>(retorno);
      this.dataSource.paginator = this.paginator;
    });
    this.changeDetectorRefs.detectChanges();
  }

  public salvarFuncionario(form: FormControl){
    let funcionarioSalvo: Veiculo;
    //this.veiculo.ativoEnum = tipoStatusSelecionado;
    this.veiculoService.salvar(this.veiculo).subscribe( (retorno: Veiculo) => {
      funcionarioSalvo = retorno;
      if(funcionarioSalvo.idVeiculo != null){
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

public buscarPorPlaca(){
  if(this.placaBusca != null){
    if(this.placaBusca.length > 1){
      this.veiculoService.listarPorPlaca(this.placaBusca).subscribe( (retorno: Veiculo[]) => {
        this.dataSource = new MatTableDataSource<Veiculo>(retorno);
        this.dataSource.paginator = this.paginator;
      });
    }
  }
}

public buscarPorModelo(){
  if(this.modeloBusca != null){
    if(this.modeloBusca.length > 1){
      this.veiculoService.listarPorModelo(this.modeloBusca).subscribe( (retorno: Veiculo[]) => {
        this.dataSource = new MatTableDataSource<Veiculo>(retorno);
        this.dataSource.paginator = this.paginator;
      });
    }
  }
}

}
