import { HttpParams, HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { ErrorHandlerService } from './../../core/error-handler.service';
import { environment } from './../../../environments/environment';
import { Veiculo } from '../../models/veiculo';

const PATH = '/veiculo';

@Injectable()
export class VeiculoService {

  courartApiUrl = '';

  headers = new HttpHeaders()
  .set('Content-Type', 'application/json; charset-UTF-8')
  .set('Cache-Control', 'no-cache')
  .set('Pragma', 'no-cache')
  .set('Expires', 'Sat, 01 Jan 2000 00:00:00 GMT');

  constructor (
    private http: HttpClient,
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
   * @param veiculo
	 * @return Observable<any>
	 */
  public salvar(veiculo: Veiculo): Observable<any> {
    let params = new HttpParams();
    let dataObserver;
    let data = new Observable(observer => (dataObserver = observer));
    let bodyJson = JSON.stringify(veiculo);
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
   * @param veiculo
	 * @return Observable<any>
	 */
  public atualizar(veiculo: Veiculo): Observable<any> {
    let params = new HttpParams();
    let dataObserver;
    let data = new Observable(observer => (dataObserver = observer));
    let bodyJson = JSON.stringify(veiculo);
    this.http
      .put(
        this.courartApiUrl + PATH + `/${veiculo.idVeiculo}`,
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
   * @param idVeiculo
   * @return Observable<any>
   */
  remover(idVeiculo: number): Observable<any> {
    let dataObserver;
    let data = new Observable(observer => (dataObserver = observer));
    this.http.delete(this.courartApiUrl + PATH +`/${idVeiculo}`)
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
   * @param idVeiculo
	 * @return Observable<any>
	 */
  public buscarPorId(idVeiculo: number): Observable<any> {
    let params = new HttpParams();
    let dataObserver;
    let data = new Observable(observer => (dataObserver = observer));
    this.http.get(this.courartApiUrl + PATH + `/${idVeiculo}`, {
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
   * @param placa
	 * @return Observable<any>
	 */
  public listarPorPlaca(placa: string): Observable<any> {
    let params = new HttpParams();
    let dataObserver;
    let data = new Observable(observer => (dataObserver = observer));
    this.http.get(this.courartApiUrl + PATH + `/buscarPorPlaca/${placa}`, {
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
   * @param modelo
	 * @return Observable<any>
	 */
  public listarPorModelo(modelo: string): Observable<any> {
    let params = new HttpParams();
    let dataObserver;
    let data = new Observable(observer => (dataObserver = observer));
    this.http.get(this.courartApiUrl + PATH + `/buscarPorModelo/${modelo}`, {
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


}
