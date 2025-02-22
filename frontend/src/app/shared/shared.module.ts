import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from '../components/navbar/navbar.component';
import { SidebarComponent } from '../components/sidebar/sidebar.component';
import {RouterLink} from '@angular/router';


@NgModule({
  declarations: [NavbarComponent, SidebarComponent],
  imports: [
    CommonModule,
    RouterLink
  ],
  exports:[
    NavbarComponent,SidebarComponent
  ]
})
export class SharedModule { }
