import * as moment from 'moment';
import 'moment/locale/pt-br';

export class Utils {

  public static converterDataParaString(data: Date): string{
    if(data != null){
      return moment(data).format('DD/MM/YYYY');
    } else {
      return null;
    }
  }

  public static converterStringParaData(data: string): Date{
    if(data != null){
      return moment(data, 'DD/MM/YYYY').toDate()
    }else {
      return null;
    }
  }

}
