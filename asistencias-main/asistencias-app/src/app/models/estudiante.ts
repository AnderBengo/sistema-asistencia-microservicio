import { Aula } from "./aula";

export class Estudiante {
    id!: number;
    nombre!: string;
    apellidoPaterno!: string;
    apellidoMaterno!: string;
    dni!: string;
    codigoEstudiante!: string;
    aula!: Aula;
}
