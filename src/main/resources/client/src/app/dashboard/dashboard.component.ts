import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {DashBoardService} from "../shared/dashboard/dashboard.service";
import {DragulaService} from 'ng2-dragula';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
  providers:[DashBoardService]
})
export class DashboardComponent implements OnInit {
  @Input() appTitle;
  lanes: Array<any>;
  @ViewChild('addLaneModal') addLaneModal;
  private newLaneModel: {};

  constructor(private dashBoardService: DashBoardService,private dragulaService :DragulaService) {
    this.newLaneModel={};
  }
  ngOnInit() {
    this.dashBoardService.getLanes().subscribe(
      data => {
        this.lanes = data;
      },
      error => console.log(error)
    )

    this.dragulaService.dropModel.subscribe((value) => {
      this.onDropModel(value.slice(1));
    });
  }

  onDropModel(args) {

    var cardId = args[0].firstElementChild.id;
    var toLane = args[1].id;
    var fromLane = args[2].id;
    if(toLane !== fromLane) {
      this.dashBoardService.transferCard(cardId, fromLane, toLane).subscribe(data => {
        console.log(data);
      },error => console.log(error));
    }
  }

  createLane(){

    var clone = JSON.parse(JSON.stringify(this.newLaneModel));
    let title = clone.title;
    //:todo change on angular validation
    if(title==null){
      alert("title must be not null");
      return;
    }else if (title.length<3||title.length>50){
      alert("title length must be between 3 and 50 characters");
      return;
    }
    this.dashBoardService.createLane(clone).subscribe((laneObj) => {
      if(laneObj) {
        this.newLaneModel = {};
        this.lanes.push(laneObj);
        this.addLaneModal.hide();
      }
    })
  }

}
