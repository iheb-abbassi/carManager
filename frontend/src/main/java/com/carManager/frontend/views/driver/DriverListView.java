package com.carManager.frontend.views.driver;

import com.carManager.frontend.dto.DriverDTO;
import com.carManager.frontend.dto.GeoCoordinate;
import com.carManager.frontend.enums.OnlineStatus;
import com.carManager.frontend.service.DriverRestService;
import com.carManager.frontend.views.BaseView;
import com.carManager.frontend.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Route(value = "drivers", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@PageTitle("Drivers")
public class DriverListView extends BaseView {

    private static final Logger log = LoggerFactory.getLogger(DriverListView.class);
    private final DriverRestService driverService;
    private final Grid<DriverDTO> grid = new Grid<>(DriverDTO.class, false);
    private final Select<OnlineStatus> statusFilter = new Select<>();
    private final Button deleteBtn = new Button("Delete Selected");
    private final Button updateLocationBtn = new Button("Update Location");

    public DriverListView(DriverRestService driverService) {
        this.driverService = driverService;

        setSizeFull();

        configureGrid();

        deleteBtn.addThemeVariants(ButtonVariant.LUMO_ERROR);
        deleteBtn.setEnabled(false);
        deleteBtn.addClickListener(e -> {
            DriverDTO selected = grid.asSingleSelect().getValue();
            if (selected != null) {
                deleteDriver(selected);
            }
        });

        updateLocationBtn.setEnabled(false);
        updateLocationBtn.addClickListener(e -> {
            DriverDTO selected = grid.asSingleSelect().getValue();
            if (selected != null) {
                openUpdateLocationDialog(selected);
            }
        });

        grid.asSingleSelect().addValueChangeListener(e -> {
            boolean hasSelection = e.getValue() != null;
            deleteBtn.setEnabled(hasSelection);
            updateLocationBtn.setEnabled(hasSelection);
        });

        HorizontalLayout actionBar = new HorizontalLayout(updateLocationBtn, deleteBtn);

        add(createFilterBar(), createForm(), actionBar, grid);
        refreshGrid();
    }

    private HorizontalLayout createFilterBar() {
        statusFilter.setItems(OnlineStatus.values());
        statusFilter.setValue(OnlineStatus.ONLINE);
        statusFilter.setLabel("Status");

        Button searchBtn = new Button("Search", e -> refreshGrid());
        searchBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        HorizontalLayout bar = new HorizontalLayout(statusFilter, searchBtn);
        bar.setAlignItems(Alignment.END);
        return bar;
    }

    private HorizontalLayout createForm() {
        TextField username = new TextField("Username");
        PasswordField password = new PasswordField("Password");

        Button createBtn = new Button("Create", e -> {
            if (username.isEmpty() || password.isEmpty()) {
                showError("Username and password are required");
                return;
            }
            try {
                driverService.createDriver(username.getValue(), password.getValue());
                username.clear();
                password.clear();
                refreshGrid();
                showSuccess("Driver created");
            } catch (Exception ex) {
                log.error("Create failed", ex);
                showError("Create failed: " + ex.getMessage());
            }
        });
        createBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        HorizontalLayout form = new HorizontalLayout(username, password, createBtn);
        form.setAlignItems(Alignment.END);
        return form;
    }

    private void configureGrid() {
        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        grid.addColumn(DriverDTO::id).setHeader("ID").setAutoWidth(true);
        grid.addColumn(DriverDTO::username).setHeader("Username").setAutoWidth(true);
        grid.addColumn(driver -> {
            GeoCoordinate coord = driver.coordinate();
            return coord != null ? String.valueOf(coord.latitude()) : "";
        }).setHeader("Latitude").setAutoWidth(true);
        grid.addColumn(driver -> {
            GeoCoordinate coord = driver.coordinate();
            return coord != null ? String.valueOf(coord.longitude()) : "";
        }).setHeader("Longitude").setAutoWidth(true);

        grid.setSizeFull();
    }

    private void openUpdateLocationDialog(DriverDTO driver) {
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("Update Location for " + driver.username());

        NumberField lonField = new NumberField("Longitude");
        lonField.setMin(-180);
        lonField.setMax(180);
        lonField.setStep(0.001);

        NumberField latField = new NumberField("Latitude");
        latField.setMin(-90);
        latField.setMax(90);
        latField.setStep(0.001);

        if (driver.coordinate() != null) {
            lonField.setValue(driver.coordinate().longitude());
            latField.setValue(driver.coordinate().latitude());
        }

        Button saveBtn = new Button("Save", e -> {
            if (lonField.isEmpty() || latField.isEmpty()) {
                showError("Longitude and latitude are required");
                return;
            }
            try {
                driverService.updateLocation(driver.id(), lonField.getValue(), latField.getValue());
                dialog.close();
                refreshGrid();
                showSuccess("Location updated");
            } catch (Exception ex) {
                log.error("Update failed", ex);
                showError("Update failed: " + ex.getMessage());
            }
        });
        saveBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        Button cancelBtn = new Button("Cancel", e -> dialog.close());

        dialog.add(new VerticalLayout(lonField, latField));
        dialog.getFooter().add(cancelBtn, saveBtn);
        dialog.open();
    }

    private void deleteDriver(DriverDTO driver) {
        try {
            log.info("Deleting driver: {}", driver.id());
            driverService.deleteDriver(driver.id());
            refreshGrid();
            showSuccess("Driver deleted");
        } catch (Exception ex) {
            log.error("Delete failed", ex);
            showError("Delete failed: " + ex.getMessage());
        }
    }

    private void refreshGrid() {
        try {
            grid.setItems(driverService.findByStatus(statusFilter.getValue()));
        } catch (Exception ex) {
            log.error("Failed to load drivers", ex);
            showError("Failed to load drivers: " + ex.getMessage());
            grid.setItems();
        }
    }
}
