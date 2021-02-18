
export class Statistics {
    public project_id: number;
    public period: String;

    constructor(project_id?: number, period?: String) {
        this.project_id = project_id;
        this.period = period;
    }

}  