require 'spec_helper'

describe 'doge-tasks-service dependencies' do
  describe command('java -version') do
    its(:stderr) { should contain 'openjdk version "1.8' }
  end

  describe command("#{ENV['CATALINA_HOME']}/bin/catalina.sh version") do
    its(:stdout) { should contain 'Apache Tomcat/8.0'}
  end
end
