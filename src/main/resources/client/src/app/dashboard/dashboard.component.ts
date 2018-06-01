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
    console.log('lol'+cardId);
    console.log('lol'+toLane);
    console.log('lol'+fromLane);
    if(toLane !== fromLane) {
      this.dashBoardService.transferCard(cardId, fromLane, toLane).subscribe(data => {
        console.log(data);
      });
    }
  }

  createLane(){
    console.log("create");
    var clone = JSON.parse(JSON.stringify(this.newLaneModel));
    this.dashBoardService.createLane(clone).subscribe((laneObj) => {
      if(laneObj) {
        this.newLaneModel = {};
        this.lanes.push(laneObj);
        this.addLaneModal.hide();
      }
    })
  }

}
