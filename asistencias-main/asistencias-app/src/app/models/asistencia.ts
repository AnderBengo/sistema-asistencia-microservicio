import { Estudiante } from "./estudiante";

export class Asistencia {
    id!: number;
    fechaHoraIngreso!: string;
    fechaHoraSalida!: string;
    ingresoConfirmado!: boolean;
    salidaConfirmado!: boolean;
    estudiante!: Estudiante;
}
