package com.carManager.frontend.views;

import com.carManager.frontend.views.car.CarListView;
import com.carManager.frontend.views.driver.DriverListView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;

public class MainLayout extends AppLayout {

    public MainLayout() {
        H1 title = new H1("CarManager Fleet Manager");
        title.getStyle().set("font-size", "var(--lumo-font-size-l)")
            .set("margin", "var(--lumo-space-m)");

        addToNavbar(new DrawerToggle(), title);

        SideNav sideNav = new SideNav();
        sideNav.addItem(new SideNavItem("Drivers", DriverListView.class));
        sideNav.addItem(new SideNavItem("Cars", CarListView.class));

        addToDrawer(sideNav);
    }
}
