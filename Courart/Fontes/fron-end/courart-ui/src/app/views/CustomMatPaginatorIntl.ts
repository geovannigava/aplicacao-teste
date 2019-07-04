import { MatPaginatorIntl } from '@angular/material';

export class CustomMatPaginatorIntl extends MatPaginatorIntl {
  itemsPerPageLabel = 'Ítens por página';
  nextPageLabel     = 'Próxima';
  previousPageLabel = 'Anterior';
  lastPageLabel = 'Última';
  firstPageLabel = 'Primeira';

  getRangeLabel = function (page, pageSize, length) {
    if (length === 0 || pageSize === 0) {
      return '0 de ' + length;
    }
    length = Math.max(length, 0);
    const startIndex = page * pageSize;
      const endIndex = startIndex < length ?
      Math.min(startIndex + pageSize, length) :
      startIndex + pageSize;
    return startIndex + 1 + ' - ' + endIndex + ' / ' + length;
  };
}
