import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';


@Injectable()
export class DashBoardService {

  constructor(private http: HttpClient) {}

  getAll(): Observable<any> {
    return this.http.get('http://localhost:8080/good-beers');
  }


  getLanes():Observable<any> {
    return this.http.get('http://localhost:8080/lane');
  }

  createLane(lane) {
    let bodyString = JSON.stringify(lane);
    console.log(lane);
    console.log(bodyString);
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json');
    return this.http.post('http://localhost:8080/lane',bodyString,{headers} )
  }

  createCard(card) {
    let bodyString = JSON.stringify(card);
    console.log(bodyString);
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json');

    return this.http.post('http://localhost:8080/card',bodyString,{headers} )
  }

  removeCard(card) {
    let bodyString = JSON.stringify(card);
    console.log(bodyString);
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json');

    return this.http.post('http://localhost:8080/deleteCard',bodyString,{headers} )
  }

  transferCard(cardId,fromLane,toLane){
    let bodyString = JSON.stringify({
      card: cardId,
      fromLane: fromLane,
      toLane: toLane
    });
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json');
    return this.http.post('http://localhost:8080/transferCard',bodyString,{headers} )
  }


}
