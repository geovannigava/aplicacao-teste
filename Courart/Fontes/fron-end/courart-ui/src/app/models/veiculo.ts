import { AtivoEnum } from './enums/atividade-enum';

export class Veiculo {
  idVeiculo: number;
  placa: string;
  ativoEnum: AtivoEnum;
  anoFabricacao: string;
  anoModelo: string;
  chassi: string;
  dataCadastro: Date;
  dataDesativacao: Date;
  modelo: string;
  cor: string;
  consumoMedio: number;
  quantidadePassageiros: number;
}
