DROP DATABASE smanager;
CREATE DATABASE smanager DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;

USE smanager;

CREATE TABLE presets (
	id BIGINT NOT NULL AUTO_INCREMENT,
	display_layout VARCHAR(30) NOT NULL,
	section_quantity INT,
	PRIMARY KEY (id, display_layout));
	
CREATE TABLE active_screens (
	id BIGINT NOT NULL AUTO_INCREMENT,
	source VARCHAR(30) NULL DEFAULT NULL,
	start_section_hz INT NULL DEFAULT NULL,
	width_sections INT NULL DEFAULT NULL,
	start_section_vt INT NULL DEFAULT NULL,
	high_sections INT NULL DEFAULT NULL,
	PRIMARY KEY (id),
	CONSTRAINT FK_ASCREENS_PRESET FOREIGN KEY (preset_id) REFERENCES presets(display_layout));

-- preload presets configuration
INSERT INTO presets VALUES (1, 'ONExONE');
INSERT INTO presets VALUES (2, 'ONExTWO');
INSERT INTO presets VALUES (3, 'ONExTHREE');
INSERT INTO presets VALUES (4, 'ONExFOUR');
INSERT INTO presets VALUES (5, 'ONExFIVE');
INSERT INTO presets VALUES (4, 'TWOxTWO');
INSERT INTO presets VALUES (6, 'TWOxTHREE');
INSERT INTO presets VALUES (8, 'TWOxFOUR');
INSERT INTO presets VALUES (10, 'TWOxFIVE');
INSERT INTO presets VALUES (9, 'THREExTHREE');
INSERT INTO presets VALUES (12, 'THREExFOUR');
INSERT INTO presets VALUES (15, 'THREExFIVE');
INSERT INTO presets VALUES (12, 'THREExFOUR');
INSERT INTO presets VALUES (15, 'THREExFIVE');
INSERT INTO presets VALUES (16, 'FOURxFOUR');
INSERT INTO presets VALUES (15, 'FOURxFIVE');
INSERT INTO presets VALUES (25, 'FIVExFIVE');

INSERT INTO active_screens VALUES ('VIDEO', 1, 1, 1, 1, 1);
INSERT INTO active_screens VALUES ('VIDEO', 1, 1, 1, 1, 2);
INSERT INTO active_screens VALUES ('CAMERA', 2, 1, 1, 1, 2);
INSERT INTO active_screens VALUES ('VIDEO', 1, 1, 1, 1, 3);
INSERT INTO active_screens VALUES ('CAMERA1', 2, 1, 1, 1, 3);
INSERT INTO active_screens VALUES ('CAMERA2', 3, 1, 1, 1, 3);
INSERT INTO active_screens VALUES ('CAMERA2', 1, 1, 1, 2, 6);
INSERT INTO active_screens VALUES ('CAMERA2', 2, 1, 1, 1, 6);
INSERT INTO active_screens VALUES ('CAMERA2', 2, 1, 2, 1, 6);