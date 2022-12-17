import { Auxiliar } from "./auxiliar";
import { Estudiante } from "./estudiante";

export class Aula {
    id!: number;
    nombre!: string;
    grado!: string;
    auxiliar!: Auxiliar;
    estudiantes!: Estudiante[];
}
