import { Routes } from '@angular/router';
import { LoginComponent } from './components/layout/login/login.component';
import { PrincipalComponent } from './components/layout/principal/principal.component';
import { ProdutoslistComponent } from './components/produtos/produtoslist/produtoslist.component';
import { ProdutosdetailsComponent } from './components/produtos/produtosdetails/produtosdetails.component';

export const routes: Routes = [
    {path: '', redirectTo: 'login', pathMatch: 'full'},
    {path: 'login', component: LoginComponent},
    {path: 'admin', component: PrincipalComponent, children: [
        {path: 'produtos', component: ProdutoslistComponent},
        {path: 'produtos/new', component: ProdutosdetailsComponent},
        {path: 'produtos/edit/:id', component: ProdutosdetailsComponent}
    ]}
];
