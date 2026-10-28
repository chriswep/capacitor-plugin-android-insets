require 'json'

package = JSON.parse(File.read(File.join(__dir__, 'package.json')))

Pod::Spec.new do |s|
  s.name = 'EmmausCapacitorPluginAndroidInsets'
  s.version = package['version']
  s.summary = package['description']
  s.license = package['license']
  s.homepage = package['repository']['url']
  s.author = package['author']
  s.source = { :git => package['repository']['url'], :tag => "v#{s.version}" }
  s.source_files = 'ios/Plugin/**/*.{swift,h,m,c,cc,mm,cpp}'
  # CAPBridgedPlugin owns registration; the legacy category advertises an obsolete method.
  s.exclude_files = 'ios/Plugin/AndroidInsetsPlugin.m'
  s.ios.deployment_target  = '15.0'
  s.dependency 'Capacitor', '~> 8.0'
  s.swift_version = '5.1'
end
