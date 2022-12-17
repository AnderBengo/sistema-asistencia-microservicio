import { Aula } from "./aula";

export class Grado {
    id!: number;
    nombre!: string;
    aulas: Aula[] = [];
}