import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'placa'
})
export class PlacaPipe implements PipeTransform {

  transform(value: string): string {
    if (value) {
      return value.replace(/([A-Z]{3})(\d{4})/g, '\$1-\$2');
    }
  }

}
