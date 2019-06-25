import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material';

@Injectable({
  providedIn: 'root'
})
export class ErrorHandlerService {

  constructor(public snackBar: MatSnackBar) { }

  handle(errorResponse: any) {
    let msg: string;

    if (typeof errorResponse === 'string') {
      msg = errorResponse;

    } else if (errorResponse.status >= 400 && errorResponse.status <= 499) {
      let errors;
      msg = 'Ocorreu um erro ao processar a sua solicitação';

      if (errorResponse.status === 403) {
        msg = 'Você não tem permissão para executar esta ação';
      }
      try {
        msg = errorResponse.error[0].mensagemUsuario;
      } catch (e) { }

      // console.error('Ocorreu um erro', errorResponse);

    } else {
      msg = 'Erro ao processar serviço remoto.';
      //console.error('Ocorreu um erro', errorResponse);
    }

      this.snackBar.open(msg, 'Erro', {
        duration: 4000,
      });

  }


}
