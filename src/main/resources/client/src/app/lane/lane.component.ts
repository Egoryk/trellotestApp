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
  laneCardId:'' ;
  constructor(private dashBoardService: DashBoardService) {

  }

  ngOnInit() {
  }

  addCard() {
    var self = this;
    if(self.newCardTitle) {

      var newCardObj = {
        _id: self.laneCardId,
        title: self.newCardTitle,
        lane: self.lane._id
      };

      //todo change ffor angular validation
      let cardTitle = newCardObj.title;
      if(cardTitle==null){
        alert("cardTitle must be not null");
        return;
      }else if (cardTitle.length<=3||cardTitle.length>50){
        alert("cardTitle length must be between 3 and 50 characters");
        return;
      }

      this.dashBoardService.createCard(newCardObj).subscribe(card => {
        if(this.laneCardId!=undefined){
          let cards = this.lane.cards;
          for (var i=0; i<cards.length; i++){
            let iterCard = cards[i];
            if(iterCard._id==newCardObj._id){
              let number = cards.indexOf(iterCard);
              cards.splice(number,1);
              break;
            }
          }
        }
        this.lane.cards.push(card);
        this.setFormVisibility(false);
        this.newCardTitle = '';
      });
    }
  }

  updateCard(card) {
    console.log("updateCard")
    console.log(card.title)
    console.log(card._id)
    this.setFormVisibility(true);
    this.newCardTitle=card.title;
    this.laneCardId=card._id;

  }
  deleteCard(card) {
    console.log("delete card ")
    console.log(card._id)
    this.dashBoardService.removeCard(card).subscribe(c => {
      let cards = this.lane.cards;
      for (var i=0; i<cards.length; i++){
        let iterCard = cards[i];
        if(iterCard._id==card._id){
          let number = cards.indexOf(iterCard);
          cards.splice(number,1);
          break;
        }
      }
    });
  }

  setFormVisibility(b) {
    this.isFormVisible=b;
  }

}
