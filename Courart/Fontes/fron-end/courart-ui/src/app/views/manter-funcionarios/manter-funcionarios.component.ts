import { Component, OnInit, ChangeDetectorRef, ViewChild, SimpleChanges } from '@angular/core';
import { MatTableDataSource, MatSnackBar, MatDialog, MatPaginator } from '@angular/material';
import { FormControl, NgForm } from '@angular/forms';

import { Utils } from './../../util/utils';
import { Funcionario } from '../../models/funcionario';
import { FuncionarioService } from './../../services/funcionario/funcionario.service';
import { DialogBoxComponent } from '../dialog-box/dialog-box.component';
import { Paginacao } from 'src/app/models/paginacao/paginacao';

@Component({
  selector: 'app-manter-funcionarios',
  templateUrl: './manter-funcionarios.component.html',
  styleUrls: ['../css/views.component.css']
})
export class ManterFuncionariosComponent implements OnInit {

  constructor(private changeDetectorRefs: ChangeDetectorRef,
    private funcionarioService: FuncionarioService,
    public snackBar: MatSnackBar,
    public dialog: MatDialog) { }

  @ViewChild(MatPaginator, {static: false}) paginator: MatPaginator;

  funcionarioPaginacao = new Paginacao();

  funcionario = new Funcionario();
  funcionarios = [];
  nomeOuParte: string;
  cpfBusca: string;
  dataTemp: Date;
  tipoBusca = 'TODOS';
  isLoading = true;

  displayedColumns: string[] = ['idFuncionario', 'nome', 'cpf',
                'dataNascimento', 'login', 'senha', 'editar', 'excluir'];

  ngOnInit() {
    this.funcionarioPaginacao.page = 0;
    this.funcionarioPaginacao.size = 5;
    this.buscarTodosFuncionarios();
  }

  ngOnChanges(changes: SimpleChanges) {
    this.funcionarioPaginacao.size = this.paginator.pageSize != undefined ? this.paginator.pageSize : this.funcionarioPaginacao.size;
    if(this.tipoBusca == 'TODOS'){
      this.buscarTodosFuncionarios();
    } else if(this.tipoBusca == 'CPF'){
      this.buscarPorCpf();
    } else {
      this.buscarPorNomeOuParte();
    }
  }

  onPaginator(){
    this.isLoading = true;
    this.funcionarioPaginacao.page = this.paginator.pageIndex;
    this.funcionarioPaginacao.size = this.paginator.pageSize != undefined ? this.paginator.pageSize : this.funcionarioPaginacao.size;
    if(this.tipoBusca == 'TODOS'){
      this.buscarTodosFuncionarios();
    } else if(this.tipoBusca == 'CPF'){
      this.buscarPorCpf();
    } else {
      this.buscarPorNomeOuParte();
    }
  }

  public onBuscar(tipoBusca: string){
    this.isLoading = true;
    this.tipoBusca = tipoBusca;
    this.funcionarioPaginacao.page = 0;
    if(this.tipoBusca == 'TODOS'){
      this.buscarTodosFuncionarios();
    } else if(this.tipoBusca == 'CPF'){
      this.buscarPorCpf();
    } else {
      this.buscarPorNomeOuParte();
    }
  }


  abrirBarraAviso(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 3000,
    });
  }

  public salvarFuncionario(form: FormControl){
    let funcionarioSalvo: Funcionario;
    this.funcionario.dataNascimento = Utils.converterDataParaString(this.dataTemp);
    this.funcionarioService.salvar(this.funcionario).subscribe( (retorno: Funcionario) => {
      funcionarioSalvo = retorno;
      if(funcionarioSalvo.idFuncionario != null){
        this.abrirBarraAviso('Funcionário Criado.', 'Sucesso');
        this.buscarTodosFuncionarios();
      }
    });
    form.reset();
  }

  public selecionarFuncionario(idFuncionario: number){
    this.funcionarioService.buscarPorId(idFuncionario).subscribe( (retorno: Funcionario) => {
      this.funcionario = retorno;
      this.dataTemp = Utils.converterStringParaData(this.funcionario.dataNascimento);
    });
  }

  public atualizarFuncionario(funcionarioAtualizado: Funcionario, form: FormControl){
    let funcionarioSalvo: Funcionario;
    funcionarioAtualizado.dataNascimento = Utils.converterDataParaString(this.dataTemp);
    this.funcionarioService.atualizar(funcionarioAtualizado).subscribe( (retorno: Funcionario) => {
      funcionarioSalvo = retorno;
      if(funcionarioSalvo.idFuncionario != null){
        this.abrirBarraAviso('Dados do Funcionário Atualizados.', 'Sucesso');
        this.buscarTodosFuncionarios();
      }
    });
    form.reset();
  }

  public dialogoConfirmacaoExclusao(idFuncionario: number): void {
      const dialogRef = this.dialog.open(DialogBoxComponent, {
        width: '250px',
        data: {}
      });
      dialogRef.afterClosed().subscribe(result => {
        if (result) {
          this.excluirFuncionario(idFuncionario);
        }
      });
  }

  public excluirFuncionario(idFuncionario: number){
    this.funcionarioService.remover(idFuncionario).subscribe( (retorno: Boolean) => {
      if(retorno){
        this.abrirBarraAviso('Funcionário Excluído.', 'Sucesso');
        this.funcionario = new Funcionario();
        this.buscarTodosFuncionarios();
      }
    });
  }

  public buscarTodosFuncionarios() {
    this.funcionarioService.listarTodos(this.funcionarioPaginacao).subscribe( (retorno) => {
      this.funcionarios = retorno.content;
      this.isLoading = false;
      this.funcionarioPaginacao.totalElements = retorno.totalElements;
      this.funcionario = new Funcionario();
    });
    this.changeDetectorRefs.detectChanges();
  }

  public buscarPorNomeOuParte() {
    if(this.nomeOuParte != null) {
      if(this.nomeOuParte.length > 0) {
        this.funcionarioService.listarPorNome(this.nomeOuParte, this.funcionarioPaginacao).subscribe( (retorno) => {
          this.funcionarios = retorno.content;
          this.isLoading = false;
          this.funcionarioPaginacao.totalElements = retorno.totalElements;
        });
      }
    }
  }

  public buscarPorCpf() {
    if(this.cpfBusca != null) {
      if(this.cpfBusca.length > 0) {
        this.funcionarioService.listarPorCpf(this.cpfBusca, this.funcionarioPaginacao).subscribe( (retorno) => {
          this.funcionarios = retorno.content;
          this.isLoading = false;
          this.funcionarioPaginacao.totalElements = retorno.totalElements;
        });
      }
    }
  }

  public resetarForm(form: FormControl){
    this.funcionario = new Funcionario();
    form.reset();
  }
}
