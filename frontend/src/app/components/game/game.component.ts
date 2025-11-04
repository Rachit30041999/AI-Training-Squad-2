import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-game',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.scss']
})
export class GameComponent {
  targetNumber: number = 0;
  userGuess: number | null = null;
  message: string = '';
  attempts: number = 0;
  gameActive: boolean = true;
  numberOfDigits: number = 1;
  digitOptions: number[] = [1, 2, 3, 4, 5];

  constructor() {
    this.initializeGame();
  }

  initializeGame(): void {
    const maxNumber = Math.pow(10, this.numberOfDigits);
    const minNumber = this.numberOfDigits === 1 ? 0 : Math.pow(10, this.numberOfDigits - 1);
    this.targetNumber = Math.floor(Math.random() * (maxNumber - minNumber)) + minNumber;
    this.userGuess = null;
    this.message = '';
    this.attempts = 0;
    this.gameActive = true;
  }

  getMinNumber(): number {
    return this.numberOfDigits === 1 ? 0 : Math.pow(10, this.numberOfDigits - 1);
  }

  getMaxNumber(): number {
    return Math.pow(10, this.numberOfDigits) - 1;
  }

  onDigitChange(): void {
    this.initializeGame();
  }

  submitGuess(): void {
    if (!this.gameActive) return;

    const minNumber = this.getMinNumber();
    const maxNumber = this.getMaxNumber();

    if (this.userGuess === null || this.userGuess < minNumber || this.userGuess > maxNumber) {
      this.message = `Please enter a valid number between ${minNumber} and ${maxNumber}!`;
      return;
    }

    this.attempts++;

    if (this.userGuess === this.targetNumber) {
      this.message = `🎉 Congratulations! You guessed correctly in ${this.attempts} attempts!`;
      this.gameActive = false;
    } else if (this.userGuess < this.targetNumber) {
      this.message = `Too low! Try again. (Attempt ${this.attempts})`;
    } else {
      this.message = `Too high! Try again. (Attempt ${this.attempts})`;
    }
  }

  newGame(): void {
    this.initializeGame();
  }
}
