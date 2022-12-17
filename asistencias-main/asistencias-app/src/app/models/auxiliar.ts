import { Aula } from "./aula";

export class Auxiliar {
    id!: number;
    nombre!: string;
    apellidoPaterno!: string;
    apellidoMaterno!: string;
    usuario!: string;
    password!: string;
    aulas: Aula[] = [];
}
