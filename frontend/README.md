# Number Guessing Game - Frontend

This is the Angular frontend for the Number Guessing Game application.

## Tech Stack
- Angular 20+
- TypeScript
- SCSS
- Angular Material (planned)

## Prerequisites
- Node.js (v18+ recommended)
- npm (v10+)
- Angular CLI

## Installation

1. Navigate to the frontend directory:
```bash
cd frontend
```

2. Install dependencies:
```bash
npm install
```

## Development Server

Run the development server:
```bash
npm start
```

Navigate to `http://localhost:4200/`. The application will automatically reload if you change any of the source files.

## Build

Build the project:
```bash
npm run build
```

The build artifacts will be stored in the `dist/` directory.

## Running Tests

Run unit tests:
```bash
npm test
```

## Project Structure

```
frontend/
├── src/
│   ├── app/
│   │   ├── components/     # UI components
│   │   ├── services/       # Business logic services
│   │   ├── models/         # TypeScript interfaces/models
│   │   ├── app.component.ts
│   │   └── app.routes.ts
│   ├── assets/             # Static assets
│   ├── environments/       # Environment configurations
│   ├── index.html
│   ├── main.ts
│   └── styles.scss
├── angular.json
├── package.json
└── tsconfig.json
```

## Features Implemented

- ✅ Angular 20+ project setup
- ✅ Project structure (components, services, models)
- ✅ Routing configuration
- ✅ Basic game component
- ✅ Responsive design with SCSS
- ⏳ Material UI integration (pending)
- ⏳ Error handling (pending)

## Next Steps

- Install and configure Angular Material
- Add comprehensive error handling
- Create additional components (login, registration, etc.)
- Integrate with backend API
