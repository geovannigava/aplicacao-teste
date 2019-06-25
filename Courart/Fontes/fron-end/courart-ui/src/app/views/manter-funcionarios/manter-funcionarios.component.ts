import { Component, OnInit, ChangeDetectorRef, ViewChild } from '@angular/core';
import { MatTableDataSource, MatPaginator, MatSnackBar, MatDialog } from '@angular/material';
import { FormControl, NgForm } from '@angular/forms';

import { Utils } from './../../util/utils';
import { Funcionario } from '../../models/funcionario';
import { FuncionarioService } from './../../services/funcionario/funcionario.service';
import { DialogBoxComponent } from '../dialog-box/dialog-box.component';

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

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  funcionario = new Funcionario();
  funcionarios = [];
  nomeOuParte: string;
  cpfBusca: string;
  dataTemp: Date;
  displayedColumns: string[] = ['idFuncionario', 'nome', 'cpf',
                'dataNascimento', 'login', 'senha', 'editar', 'excluir'];
  dataSource: MatTableDataSource<Funcionario>;

  ngOnInit() {
    this.buscarTodosFuncionarios();
  }

  abrirBarraAviso(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 3000,
    });
  }

  private buscarTodosFuncionarios() {
    this.funcionarioService.listarTodos().subscribe( (retorno: Funcionario[]) => {
      this.funcionarios = retorno;
      this.dataSource = new MatTableDataSource<Funcionario>(retorno);
      this.dataSource.paginator = this.paginator;
      this.funcionario = new Funcionario();
    });
    this.changeDetectorRefs.detectChanges();
  }

  private salvarFuncionario(form: FormControl){
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

  private selecionarFuncionario(idFuncionario: number){
    this.funcionarioService.buscarPorId(idFuncionario).subscribe( (retorno: Funcionario) => {
      this.funcionario = retorno;
      this.dataTemp = Utils.converterStringParaData(this.funcionario.dataNascimento);
    });
  }

  private atualizarFuncionario(funcionarioAtualizado: Funcionario, form: FormControl){
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

  dialogoConfirmacaoExclusao(idFuncionario: number): void {
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

  private excluirFuncionario(idFuncionario: number){
    this.funcionarioService.remover(idFuncionario).subscribe( (retorno: Boolean) => {
      if(retorno){
        this.abrirBarraAviso('Funcionário Excluído.', 'Sucesso');
        this.funcionario = new Funcionario();
        this.buscarTodosFuncionarios();
      }
    });
  }

  private buscarPorNomeOuParte(){
    if(this.nomeOuParte != null){
      if(this.nomeOuParte.length > 1){
        this.funcionarioService.listarPorNome(this.nomeOuParte).subscribe( (retorno: Funcionario[]) => {
          this.funcionarios = retorno;
          this.dataSource = new MatTableDataSource<Funcionario>(retorno);
          this.dataSource.paginator = this.paginator;
        });
      }
    }
  }

  private buscarPorCpf(){
    if(this.cpfBusca != null){
      if(this.cpfBusca.length > 1){
        this.funcionarioService.listarPorCpf(this.cpfBusca).subscribe( (retorno: Funcionario[]) => {
          this.funcionarios = retorno;
          this.dataSource = new MatTableDataSource<Funcionario>(retorno);
          this.dataSource.paginator = this.paginator;
        });
      }
    }
  }

  private resetarForm(form: FormControl){
    this.funcionario = new Funcionario();
    form.reset();
  }
}
