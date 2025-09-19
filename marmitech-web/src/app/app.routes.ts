import { Routes } from '@angular/router';
import { LoginComponent } from './components/layout/login/login.component';
import { PrincipalComponent } from './components/layout/principal/principal.component';
import { ProdutoslistComponent } from './components/produtos/produtoslist/produtoslist.component';
import { ProdutosdetailsComponent } from './components/produtos/produtosdetails/produtosdetails.component';
import { CategoriaslistComponent } from './components/categorias/categoriaslist/categoriaslist.component';
import { CategoriasdetailsComponent } from './components/categorias/categoriasdetails/categoriasdetails.component';
import { PedidoslistComponent } from './components/pedidos/pedidoslist/pedidoslist.component';
import { PedidosdetailsComponent } from './components/pedidos/pedidosdetails/pedidosdetails.component';
import { PedidosItemlistComponent } from './components/pedidosItem/pedidos-itemlist/pedidos-itemlist.component';
import { HistoricolistComponent } from './components/hisotrico/historicolist/historicolist.component';
import { HistoricodetailsComponent } from './components/hisotrico/historicodetails/historicodetails.component';
import { Cliente } from './models/cliente';
import { ClientelistComponent } from './components/cliente/clientelist/clientelist.component';
import { clienteDetailsComponent } from './components/cliente/clientedetails/clientedetails.component';



export const routes: Routes = [
    { path: '', redirectTo: 'login', pathMatch: 'full' },
    { path: 'login', component: LoginComponent },
    {
        path: 'admin', component: PrincipalComponent, children: [
            { path: 'produtos', component: ProdutoslistComponent },
            { path: 'produtos/new', component: ProdutosdetailsComponent },
            { path: 'produtos/edit/:id', component: ProdutosdetailsComponent },
            { path: 'pedidos', component: PedidoslistComponent },
            { path: 'pedidos/new', component: PedidosdetailsComponent },
            { path: 'pedidos/edit/:id', component: PedidosdetailsComponent },
            { path: 'pedidosItem', component: PedidosItemlistComponent },
            { path: 'categorias', component: CategoriaslistComponent },
            { path: 'categorias/new', component: CategoriasdetailsComponent },
            { path: 'categorias/edit/:id', component: CategoriasdetailsComponent },
            { path: 'historicos', component: HistoricolistComponent },
            { path: 'historicos/:id', component: HistoricodetailsComponent },
            {path:'cliente',component:ClientelistComponent},
            {path:'cliente/new',component:clienteDetailsComponent},
            {path:'cliente/edit/:id',component:clienteDetailsComponent}

        ]
    }
];
