import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  template: `
    <div class="app-container">
      <header>
        <h1>Number Guessing Game</h1>
      </header>
      <main>
        <router-outlet></router-outlet>
      </main>
      <footer>
        <p>&copy; 2025 Number Guessing Game</p>
      </footer>
    </div>
  `,
  styles: [`
    .app-container {
      display: flex;
      flex-direction: column;
      min-height: 100vh;
    }

    header {
      background-color: #3f51b5;
      color: white;
      padding: 1.5rem;
      text-align: center;
      box-shadow: 0 2px 4px rgba(0,0,0,0.1);
    }

    header h1 {
      margin: 0;
      font-size: 2rem;
      font-weight: 500;
    }

    main {
      flex: 1;
      padding: 2rem;
      max-width: 1200px;
      width: 100%;
      margin: 0 auto;
    }

    footer {
      background-color: #f5f5f5;
      padding: 1rem;
      text-align: center;
      color: #666;
      border-top: 1px solid #e0e0e0;
    }

    footer p {
      margin: 0;
    }
  `]
})
export class AppComponent {
  title = 'Number Guessing Game';
}
