import { Component } from '@angular/core';
import { AttackMapComponent } from '../attack-map/attack-map';
import { AlertsIntensityChart } from '../alerts-intensity-chart/alerts-intensity-chart';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [AttackMapComponent, AlertsIntensityChart],
  templateUrl: './dashboard.html',
  styleUrls: ['./dashboard.css']
})
export default class Dashboard {

}
