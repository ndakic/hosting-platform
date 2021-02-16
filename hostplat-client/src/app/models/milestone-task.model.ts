export class MilestoneTask {
    public task_id: number;
    public milestone_id: number;
  
  

    constructor(task_id?: number, milestone_id?: number) {
        this.task_id = task_id;
        this.milestone_id = milestone_id;
    }


} 