DROP DATABASE smanager;
CREATE DATABASE smanager DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;

USE smanager;

CREATE TABLE display (
	id BIGINT NOT NULL AUTO_INCREMENT,
	width INT NULL DEFAULT NULL,
	high INT NULL DEFAULT NULL,
	panel_layout VARCHAR(32) NULL DEFAULT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE hardware (
	id BIGINT NOT NULL AUTO_INCREMENT,
	active BOOLEAN NOT NULL DEFAULT 0,
	model_name VARCHAR(32) NULL DEFAULT NULL,
	display_id BIGINT NULL DEFAULT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (display_id) REFERENCES display(id)
);

CREATE TABLE hardware_sources (
	hardware_id BIGINT NOT NULL,
	sources VARCHAR(32) NULL DEFAULT NULL,
	FOREIGN KEY (hardware_id) REFERENCES hardware (id)
);

CREATE TABLE presets (
	id BIGINT NOT NULL AUTO_INCREMENT,
	name VARCHAR(32) NULL DEFAULT NULL,
	hardware_id BIGINT NULL DEFAULT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (hardware_id) REFERENCES hardware(id) ON UPDATE CASCADE ON DELETE CASCADE
);
	
CREATE TABLE preset_windows (
	id BIGINT NOT NULL AUTO_INCREMENT,
	name VARCHAR(32) NULL DEFAULT NULL,
	source VARCHAR(32) NULL DEFAULT NULL,
	x_top_left INT NULL DEFAULT NULL,
	y_top_left INT NULL DEFAULT NULL,
	x_bottom_right INT NULL DEFAULT NULL,
	y_bottom_right INT NULL DEFAULT NULL,
	z_index INT NULL DEFAULT NULL,
	preset_id BIGINT NOT NULL DEFAULT 0,
	PRIMARY KEY (id),
	FOREIGN KEY (preset_id) REFERENCES presets(id) ON UPDATE CASCADE ON DELETE CASCADE
);

-- preload configuration data
INSERT INTO display(width, high, panel_layout) VALUES (600, 600, 'ONExONE');
INSERT INTO display(width, high, panel_layout) VALUES (1200, 600, 'ONExTWO');
INSERT INTO display(width, high, panel_layout) VALUES (1200, 1200, 'TWOxTWO');
INSERT INTO display(width, high, panel_layout) VALUES (3000, 1800, 'THREExFIVE');

INSERT INTO hardware(active, model_name, display_id) VALUES (0, 'PANASONIC', 1);
INSERT INTO hardware(active, model_name, display_id) VALUES (0, 'SHARP', 3);
INSERT INTO hardware(active, model_name, display_id) VALUES (1, 'SONY', 4);

INSERT INTO hardware_sources(hardware_id, sources) VALUES (3, 'Input 1');
INSERT INTO hardware_sources(hardware_id, sources) VALUES (3, 'Input 2');
INSERT INTO hardware_sources(hardware_id, sources) VALUES (3, 'Input 3');
INSERT INTO hardware_sources(hardware_id, sources) VALUES (3, 'Input 4');
INSERT INTO hardware_sources(hardware_id, sources) VALUES (3, 'testvnc');
INSERT INTO hardware_sources(hardware_id, sources) VALUES (3, 'video');
INSERT INTO hardware_sources(hardware_id, sources) VALUES (3, 'web');

INSERT INTO presets(name, hardware_id) VALUES ('Preset1', 3);
INSERT INTO presets(name, hardware_id) VALUES ('Preset2', 3);

INSERT INTO preset_windows(name,source,x_top_left,y_top_left,x_bottom_right,y_bottom_right,z_index,preset_id) VALUES ('Window 0','video',800,0,1600,600,0,1);
INSERT INTO preset_windows(name,source,x_top_left,y_top_left,x_bottom_right,y_bottom_right,z_index,preset_id) VALUES ('Window 1','testvnc',800,600,1600,1200,4,1);
INSERT INTO preset_windows(name,source,x_top_left,y_top_left,x_bottom_right,y_bottom_right,z_index,preset_id) VALUES ('Window 2','video',0,600,800,1200,1,1);
INSERT INTO preset_windows(name,source,x_top_left,y_top_left,x_bottom_right,y_bottom_right,z_index,preset_id) VALUES ('Window 3','Input 4',400,300,800,600,3,1);
INSERT INTO preset_windows(name,source,x_top_left,y_top_left,x_bottom_right,y_bottom_right,z_index,preset_id) VALUES ('Window 4','web',400,0,800,300,2,1);

INSERT INTO preset_windows(name,source,x_top_left,y_top_left,x_bottom_right,y_bottom_right,z_index,preset_id) VALUES ('Window 0','Input 1',0,0,1200,1200,0,2);
INSERT INTO preset_windows(name,source,x_top_left,y_top_left,x_bottom_right,y_bottom_right,z_index,preset_id) VALUES ('Window 1','Input 2',1200,0,1600,600,0,2);
INSERT INTO preset_windows(name,source,x_top_left,y_top_left,x_bottom_right,y_bottom_right,z_index,preset_id) VALUES ('Window 2','Input 3',1200,600,1600,1200,0,2);