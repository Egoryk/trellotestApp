import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { AppComponent } from './app.component';
import { DragulaModule } from 'ng2-dragula/ng2-dragula';
import {DashBoardService} from "./shared/dashboard/dashboard.service";
import { DashboardComponent } from './dashboard/dashboard.component';
import { CardComponent } from './card/card.component';
import { LaneComponent } from './lane/lane.component';
import {FormsModule} from "@angular/forms";
import {BsDropdownModule, ModalModule} from "ngx-bootstrap";
import {HttpModule} from "@angular/http";

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    CardComponent,
    LaneComponent
  ],
  imports: [
    BsDropdownModule.forRoot(),
    BrowserModule,
    HttpClientModule,
    HttpModule,
    FormsModule,
    DragulaModule,
    NgbModule,
    ModalModule
  ],
  providers: [],
  bootstrap: [AppComponent,DashboardComponent]
})
export class AppModule { }
