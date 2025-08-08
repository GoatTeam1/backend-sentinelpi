import { isPlatformBrowser } from '@angular/common';
import { AfterViewInit, Component, ElementRef, Inject, PLATFORM_ID, ViewChild } from '@angular/core';

@Component({
  selector: 'app-home',
  imports: [],
  templateUrl: './home.html',
  styleUrl: './home.css'
})

export default class Home implements AfterViewInit {
  @ViewChild('attackMap') mapRef!: ElementRef<HTMLDivElement>;

  constructor(@Inject(PLATFORM_ID) private platformId: Object) {}

  ngAfterViewInit(): void {
    if (!isPlatformBrowser(this.platformId)) return;

    const map = this.mapRef.nativeElement;
    const mapWidth = map.offsetWidth;
    const mapHeight = map.offsetHeight;

    const createAttackDot = () => {
      const dot = document.createElement('div');
      dot.classList.add('attack-dot');

      const isDanger = Math.random() < 0.3;
      dot.classList.add(isDanger ? 'bg-red-500' : 'bg-green-500');
      if (isDanger) dot.classList.add('attack-dot-danger');

      const padding = 20;
      dot.style.left = `${Math.random() * (mapWidth - padding * 2) + padding}px`;
      dot.style.top = `${Math.random() * (mapHeight - padding * 2) + padding}px`;
      dot.style.position = 'absolute';
      dot.style.animationDelay = `${Math.random() * 2}s`;

      map.appendChild(dot);
      setTimeout(() => dot.remove(), 10000 + Math.random() * 5000);
    };

    for (let i = 0; i < 15; i++) createAttackDot();
    setInterval(createAttackDot, 2000);
  }
}