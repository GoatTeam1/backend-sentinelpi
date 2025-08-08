import { Routes } from '@angular/router';

export const routes: Routes = [
    {
        path: 'login', title: 'Login', loadComponent: () => import('./components/auth/login/login')
    },
    {
        path: 'dashboard', title: 'Dashboard', loadComponent: () => import('./components/dashboard/home/home')
    },
    {
        path:'**',
        redirectTo:'login',
        pathMatch:'full'
    }
];
