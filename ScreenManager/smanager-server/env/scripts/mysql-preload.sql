DROP DATABASE smanager;
CREATE DATABASE smanager DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;

USE smanager;

CREATE TABLE presets (
	id BIGINT NOT NULL AUTO_INCREMENT,
	display_layout VARCHAR(32) NOT NULL,
	PRIMARY KEY (id));

CREATE TABLE active_screens (
	id BIGINT NOT NULL AUTO_INCREMENT,
	source VARCHAR(255) NULL DEFAULT NULL,
	start_section_hz INT NULL DEFAULT NULL,
	width_sections INT NULL DEFAULT NULL,
	start_section_vt INT NULL DEFAULT NULL,
	high_sections INT NULL DEFAULT NULL,
	preset_id BIGINT NOT NULL DEFAULT 0,
	PRIMARY KEY (id),
	FOREIGN KEY (preset_id) REFERENCES presets(id) ON UPDATE CASCADE ON DELETE CASCADE);

-- preload presets configuration
INSERT INTO presets(display_layout) VALUES ('ONExONE');
INSERT INTO presets(display_layout) VALUES ('ONExTWO');
INSERT INTO presets(display_layout) VALUES ('ONExTHREE');
INSERT INTO presets(display_layout) VALUES ('ONExFOUR');
INSERT INTO presets(display_layout) VALUES ('ONExFIVE');
INSERT INTO presets(display_layout) VALUES ('TWOxTWO');
INSERT INTO presets(display_layout) VALUES ('TWOxTHREE');
INSERT INTO presets(display_layout) VALUES ('TWOxFOUR');
INSERT INTO presets(display_layout) VALUES ('TWOxFIVE');
INSERT INTO presets(display_layout) VALUES ('THREExTHREE');
INSERT INTO presets(display_layout) VALUES ('THREExFOUR');
INSERT INTO presets(display_layout) VALUES ('THREExFIVE');
INSERT INTO presets(display_layout) VALUES ('FOURxFOUR');
INSERT INTO presets(display_layout) VALUES ('FOURxFIVE');
INSERT INTO presets(display_layout) VALUES ('FIVExFIVE');

INSERT INTO active_screens(source, start_section_hz, width_sections, start_section_vt, high_sections, preset_id) VALUES ('VIDEO', 1, 1, 1, 1, 1);
INSERT INTO active_screens(source, start_section_hz, width_sections, start_section_vt, high_sections, preset_id) VALUES ('VIDEO', 1, 1, 1, 1, 2);
INSERT INTO active_screens(source, start_section_hz, width_sections, start_section_vt, high_sections, preset_id) VALUES ('CAMERA', 2, 1, 1, 1, 2);
INSERT INTO active_screens(source, start_section_hz, width_sections, start_section_vt, high_sections, preset_id) VALUES ('VIDEO', 1, 1, 1, 1, 3);
INSERT INTO active_screens(source, start_section_hz, width_sections, start_section_vt, high_sections, preset_id) VALUES ('CAMERA1', 2, 1, 1, 1, 3);
INSERT INTO active_screens(source, start_section_hz, width_sections, start_section_vt, high_sections, preset_id) VALUES ('CAMERA2', 3, 1, 1, 1, 3);
INSERT INTO active_screens(source, start_section_hz, width_sections, start_section_vt, high_sections, preset_id) VALUES ('CAMERA2', 1, 1, 1, 2, 6);
INSERT INTO active_screens(source, start_section_hz, width_sections, start_section_vt, high_sections, preset_id) VALUES ('CAMERA2', 2, 1, 1, 1, 6);
INSERT INTO active_screens(source, start_section_hz, width_sections, start_section_vt, high_sections, preset_id) VALUES ('CAMERA2', 2, 1, 2, 1, 6);