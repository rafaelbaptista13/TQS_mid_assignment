import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BycityComponent } from './bycity/bycity.component';
import { BydayComponent } from './byday/byday.component';
import { CacheComponent } from './cache/cache.component';
import { HomepageComponent } from './homepage/homepage.component';

const routes: Routes = [
  {path: '', component: HomepageComponent},
  {path: 'bycity', component: BycityComponent},
  {path: 'byday', component: BydayComponent},
  {path: 'cache', component: CacheComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
