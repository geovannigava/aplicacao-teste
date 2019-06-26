import { Utils } from './../../util/utils';
import { HttpParams, HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

import { ErrorHandlerService } from './../../core/error-handler.service';
import { environment } from './../../../environments/environment';
import { Funcionario } from '../../models/funcionario';

const PATH = '/funcionario';

@Injectable()
export class FuncionarioService {

  courartApiUrl = '';

  headers = new HttpHeaders()
  .set('Content-Type', 'application/json; charset-UTF-8')
  .set('Cache-Control', 'no-cache')
  .set('Pragma', 'no-cache')
  .set('Expires', 'Sat, 01 Jan 2000 00:00:00 GMT');

  constructor (
    private http: HttpClient,
    public router: Router,
    private erroHandlerService: ErrorHandlerService
  ) { this.courartApiUrl = environment.courartApiUrl; }


	/**
	 * Listar Todos os Funcionarios.
	 *
	 * @return Observable<any>
	 */
  public listarTodos(): Observable<any> {
    let params = new HttpParams();
    let dataObserver;
    let data = new Observable(observer => (dataObserver = observer));
    this.http.get(this.courartApiUrl + PATH, {
      headers: this.headers,
      params: params,
      reportProgress: true})
      .subscribe(dados => {
        if (dataObserver != null) {
          dataObserver.next(dados);
        }
      }, err => {
        this.erroHandlerService.handle(err);
      });
      return data;
  }

  /**
	 * Salvar Funcionario
	 *
   * @param funcionario
	 * @return Observable<any>
	 */
  public salvar(funcionario: Funcionario): Observable<any> {
    let params = new HttpParams();
    let dataObserver;
    let data = new Observable(observer => (dataObserver = observer));
    let bodyJson = JSON.stringify(funcionario);
    this.http
      .post(
        this.courartApiUrl + PATH,
        bodyJson,
        { headers: this.headers }
      )
      .subscribe(dados => {
        if (dataObserver != null) {
          dataObserver.next(dados);
        }
      }, err => {
        this.erroHandlerService.handle(err);
      });
      return data;
  }

  /**
	 * Atualizar Funcionario
	 *
   * @param funcionario
	 * @return Observable<any>
	 */
  public atualizar(funcionario: Funcionario): Observable<any> {
    let params = new HttpParams();
    let dataObserver;
    let data = new Observable(observer => (dataObserver = observer));
    let bodyJson = JSON.stringify(funcionario);
    this.http
      .put(
        this.courartApiUrl + PATH + `/${funcionario.idFuncionario}`,
        bodyJson,
        { headers: this.headers }
      )
      .subscribe(dados => {
        if (dataObserver != null) {
          dataObserver.next(dados);
        }
      }, err => {
        this.erroHandlerService.handle(err);
      });
      return data;
  }

  /**
   * Remove Funcionario
   *
   * @param idFuncionario
   * @return Observable<any>
   */
  remover(idFuncionario: number): Observable<any> {
    let dataObserver;
    let data = new Observable(observer => (dataObserver = observer));
    this.http.delete(this.courartApiUrl + PATH +`/${idFuncionario}`)
    .subscribe(dados => {
      if (dataObserver != null) {
        dataObserver.next(dados);
      }
    }, err => {
      this.erroHandlerService.handle(err);
    });
    return data;
  }

  /**
	 * Buscar Funcionario por Id
	 *
   * @param idFuncionario
	 * @return Observable<any>
	 */
  public buscarPorId(idFuncionario: number): Observable<any> {
    let params = new HttpParams();
    let dataObserver;
    let data = new Observable(observer => (dataObserver = observer));
    this.http.get(this.courartApiUrl + PATH + `/${idFuncionario}`, {
      headers: this.headers,
      params: params,
      reportProgress: true})
      .subscribe(dados => {
        if (dataObserver != null) {
          dataObserver.next(dados);
        }
      }, err => {
        this.erroHandlerService.handle(err);
      });
      return data;
  }

  /**
	 * Listar Funcionários por nome.
	 *
   * @param nome
	 * @return Observable<any>
	 */
  public listarPorNome(nome: string): Observable<any> {
    let params = new HttpParams();
    let dataObserver;
    let data = new Observable(observer => (dataObserver = observer));
    this.http.get(this.courartApiUrl + PATH + `/buscarPorNome/${nome}`, {
      headers: this.headers,
      params: params,
      reportProgress: true})
      .subscribe(dados => {
        if (dataObserver != null) {
          dataObserver.next(dados);
        }
      }, err => {
        this.erroHandlerService.handle(err);
      });
      return data;
  }

  /**
	 * Listar Funcionários por cpf.
	 *
   * @param cpf
	 * @return Observable<any>
	 */
  public listarPorCpf(cpf: string): Observable<any> {
    let params = new HttpParams();
    let dataObserver;
    let data = new Observable(observer => (dataObserver = observer));
    this.http.get(this.courartApiUrl + PATH + `/buscarPorCpf/${cpf}`, {
      headers: this.headers,
      params: params,
      reportProgress: true})
      .subscribe(dados => {
        if (dataObserver != null) {
          dataObserver.next(dados);
        }
      }, err => {
        this.erroHandlerService.handle(err);
      });
      return data;
  }

  /**
	 * Buscar Relatorio de aniversariantes por periodo
	 *
   * @param inicio
   * @param fim
	 * @return Observable<Blob>
	 */
  public buscarRelatorioAniversariantes(inicio: Date, fim: Date): Observable<Blob> {
    let params = new HttpParams().set('inicio', Utils.converterDataParaString(inicio));
    params = params.set('fim', Utils.converterDataParaString(fim));
    const httpOptions = {
      responseType: 'blob' as 'json',
      params: params
    };
    return this.http.get<Blob>(
      this.courartApiUrl + PATH + '/relatorio',
      httpOptions
    );
  }


}
