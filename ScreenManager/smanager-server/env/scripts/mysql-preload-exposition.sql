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
INSERT INTO display(width, high, panel_layout) VALUES (6830, 2304, 'THREExFIVE');

INSERT INTO hardware(active, model_name, display_id) VALUES (1, 'ATANOR_VIDEOWALL', 1);

INSERT INTO hardware_sources(hardware_id, sources) VALUES (1, 'bg');
INSERT INTO hardware_sources(hardware_id, sources) VALUES (1, 'ppt_control');
INSERT INTO hardware_sources(hardware_id, sources) VALUES (1, 'ppt_atanor');
INSERT INTO hardware_sources(hardware_id, sources) VALUES (1, 'ppt_education');
INSERT INTO hardware_sources(hardware_id, sources) VALUES (1, 'ppt_avp');
INSERT INTO hardware_sources(hardware_id, sources) VALUES (1, 'fine_art');
INSERT INTO hardware_sources(hardware_id, sources) VALUES (1, '4k');
INSERT INTO hardware_sources(hardware_id, sources) VALUES (1, 'ppt_atanor5x3');
INSERT INTO hardware_sources(hardware_id, sources) VALUES (1, 'c1');
INSERT INTO hardware_sources(hardware_id, sources) VALUES (1, 'c2');
INSERT INTO hardware_sources(hardware_id, sources) VALUES (1, '');

INSERT INTO presets(name, hardware_id) VALUES ('p_1', 1);
INSERT INTO presets(name, hardware_id) VALUES ('p_2', 1);
INSERT INTO presets(name, hardware_id) VALUES ('p_3', 1);

-- p_1 windows:
INSERT INTO preset_windows(name,source,x_top_left,y_top_left,x_bottom_right,y_bottom_right,z_index,preset_id) VALUES ('Window 0','bg',-24,-1067,1342,-299,1,1);
INSERT INTO preset_windows(name,source,x_top_left,y_top_left,x_bottom_right,y_bottom_right,z_index,preset_id) VALUES ('Window 1','ppt_control',0,0,1366,768,3,1);
INSERT INTO preset_windows(name,source,x_top_left,y_top_left,x_bottom_right,y_bottom_right,z_index,preset_id) VALUES ('Window 2','ppt_atanor',0,768,1366,1536,4,1);
INSERT INTO preset_windows(name,source,x_top_left,y_top_left,x_bottom_right,y_bottom_right,z_index,preset_id) VALUES ('Window 3','ppt_education',0,1536,1366,2304,5,1);
INSERT INTO preset_windows(name,source,x_top_left,y_top_left,x_bottom_right,y_bottom_right,z_index,preset_id) VALUES ('Window 4','scada',5464,0,6830,768,0,1);
INSERT INTO preset_windows(name,source,x_top_left,y_top_left,x_bottom_right,y_bottom_right,z_index,preset_id) VALUES ('Window 5','fine_art',5464,768,6830,1536,2,1);
INSERT INTO preset_windows(name,source,x_top_left,y_top_left,x_bottom_right,y_bottom_right,z_index,preset_id) VALUES ('Window 6','4k',5464,1536,6830,2304,6,1);
INSERT INTO preset_windows(name,source,x_top_left,y_top_left,x_bottom_right,y_bottom_right,z_index,preset_id) VALUES ('Window 7','ppt_avp',1366,0,5465,2304,7,1);

-- p_2 windows:
INSERT INTO preset_windows(name,source,x_top_left,y_top_left,x_bottom_right,y_bottom_right,z_index,preset_id) VALUES ('Window 0','bg',-121,-982,1245,-214,1,2);
INSERT INTO preset_windows(name,source,x_top_left,y_top_left,x_bottom_right,y_bottom_right,z_index,preset_id) VALUES ('Window 1','4k',0,0,4098,2304,2,2);
INSERT INTO preset_windows(name,source,x_top_left,y_top_left,x_bottom_right,y_bottom_right,z_index,preset_id) VALUES ('Window 2','scada',4098,768,6830,2304,4,2);
INSERT INTO preset_windows(name,source,x_top_left,y_top_left,x_bottom_right,y_bottom_right,z_index,preset_id) VALUES ('Window 3','c2',5464,0,6830,768,0,2);
INSERT INTO preset_windows(name,source,x_top_left,y_top_left,x_bottom_right,y_bottom_right,z_index,preset_id) VALUES ('Window 4','c1',4098,0,5464,768,3,2);

-- p_3 windows:
INSERT INTO preset_windows(name,source,x_top_left,y_top_left,x_bottom_right,y_bottom_right,z_index,preset_id) VALUES ('Window 0','',5760,0,5760,0,0,3);
INSERT INTO preset_windows(name,source,x_top_left,y_top_left,x_bottom_right,y_bottom_right,z_index,preset_id) VALUES ('Window 1','ppt_atanor5x3',0,0,6830,2304,1,3);