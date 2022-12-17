import { SimulacionAulaItem } from "./simulacion-aula-item";

export class SimulacionAula {
    id!: number;
    nombre!: string;
    gradoId!: number;
    fechaInicio!: string;
    fechaFin!: string;
    activo!: boolean;
    cantidadSimulacion!: number;
    itemsSimulacion: SimulacionAulaItem[] = [];
}