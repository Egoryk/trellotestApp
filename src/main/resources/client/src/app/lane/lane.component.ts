import {Component, Input, OnInit} from '@angular/core';
import {DashBoardService} from "../shared/dashboard/dashboard.service";

@Component({
  selector: 'lane',
  templateUrl: './lane.component.html',
  styleUrls: ['./lane.component.css'],
  providers: [DashBoardService]

})
export class LaneComponent implements OnInit {

  @Input('data') lane;
  isFormVisible:false;
  newCardTitle:'' ;
  constructor(private dashBoardService: DashBoardService) {

  }

  ngOnInit() {
  }

  addCard() {
    var self = this;
    if(self.newCardTitle) {

      var newCardObj = {
        title: self.newCardTitle,
        lane: self.lane._id
      };

      this.dashBoardService.createCard(newCardObj).subscribe(card => {
        this.lane.cards.push(card);
        this.setFormVisibility(false);
        this.newCardTitle = '';
      });
    }
  }

  updateCard(card ) {
    console.log("updateCard")
    console.log(card.title)
    this.setFormVisibility(true);
    this.newCardTitle=card.title;

  }
  deleteCard() {
    console.log("delete card ")

  }

  setFormVisibility(b) {
    this.isFormVisible=b;
  }
}
